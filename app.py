from flask import Flask, jsonify, render_template, request, flash, url_for, redirect
import os
import sqlite3
from datetime import datetime
from markdown import markdown
from flasgger import Swagger
import requests
from flask_restx import Api, Resource

from werkzeug.utils import secure_filename

app = Flask(__name__)
swagger = Swagger(app)
#####################스웨거 주소 ######################
# http://127.0.0.1:5002/apidocs/
# 5002는 포트번호이므로 변경해도 상관없음(default)

UPLOAD_FOLDER = 'static/uploads'
ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg'}

app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

app.config['SWAGGER'] = {
    'title': 'My API',
    'uiversion': 3
}

DATABASE = 'users.db'

@app.route('/api/some_endpoint', methods=['GET'])
def some_endpoint():
    """
    API 엔드포인트 설명
    ---
    responses:
      200:
        description: 성공 응답 설명
    """
    return jsonify({"message": "success"})

@app.route('/')
def index():

    return render_template('projects_list.html')  # << html 파일이름

    #projects_list 부분 확인하려면 아래에 있는 주석 해제 및 위에 return 주석

    #########################################################################################
    #conn = get_db()
    #cursor = conn.cursor()
    #cursor.execute("""SELECT * FROM projects_recruitment""")
    #projects = cursor.fetchall()
    #conn.close()

    #return render_template('projects_list.html', projects=projects)
    #########################################################################################



# test api
@app.route('/api/greet', methods=['GET'])
def greet():
    """
        테스트 API 엔드포인트
        ---
        responses:
          200:
            description: 테스트 메시지 반환
        """
    return jsonify({"message": "test 한글?"})


# 데이터베이스 연결 함수
def get_db():
    conn = sqlite3.connect(DATABASE)
    return conn


# 일반 사용자 회원가입 api
@app.route('/sign_up_user', methods=['POST'])
def sign_up_user():
    """
            일반 사용자 회원가입 api
            ---
            responses:
              400:
                description: 사용자 정보를 입력받아 아이디 중복체크 확인 후 DB에 저장
            """
    data = request.get_json()

    id = data['id']
    password = data['password']
    name = data['name']
    nickname = data['nickname']
    email = data['email']
    git_address = data['git_address']

    conn = get_db()
    cursor = conn.cursor()

    # 중복된 아이디 체크 / user table에서
    cursor.execute("SELECT * FROM users WHERE id=?", (id,))
    if cursor.fetchone():
        return jsonify({"error": "id already exists"}), 400

    # 중복된 아이디 체크 / admin table에서
    cursor.execute("SELECT * FROM admin WHERE id=?", (id,))
    if cursor.fetchone():
        return jsonify({"error": "id already exists"}), 400

    # 사용자 데이터 삽입
    cursor.execute("""
            INSERT INTO users (id, password, name, nickname, email, git_address)
            VALUES (?, ?, ?, ?, ?, ?)
        """, (id, password, name, nickname, email, git_address))

    conn.commit()
    conn.close()

    return jsonify({"message": "User registered successfully!"}), 201


# 관리자 회원가입 api
@app.route('/sign_up_admin', methods=['POST'])
def sign_up_admin():
    data = request.get_json()

    id = data['id']
    password = data['password']
    name = data['name']
    position = data['position']
    email = data['email']

    conn = get_db()
    cursor = conn.cursor()

    # 중복된 아이디 체크 / user table에서
    cursor.execute("SELECT * FROM users WHERE id=?", (id,))
    if cursor.fetchone():
        return jsonify({"error": "id already exists"}), 400

    # 중복된 아이디 체크 / admin table에서
    cursor.execute("SELECT * FROM admin WHERE id=?", (id,))
    if cursor.fetchone():
        return jsonify({"error": "id already exists"}), 400

    # 사용자 데이터 삽입
    cursor.execute("""
            INSERT INTO admin (id, password, name, position, email)
            VALUES (?, ?, ?, ?, ?)
        """, (id, password, name, position, email))

    conn.commit()
    conn.close()

    return jsonify({"message": "User registered successfully!"}), 201


@app.route('/check_userid', methods=['POST'])
def check_userid():
    userid = request.json.get('userId')
    # DB에서 해당 userid 확인
    # 예: user = db.find_one({"userId": userid})
    # if user:
    #     return jsonify(available=False)
    return jsonify(available=True)


# 프로젝트 모집 글(사용자) api

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app.route('/create_project', methods=['POST'])
def create_project():
    #이미지 업로드
    if 'image' not in request.files:
        return jsonify({"message": "Image not found!"}), 400

    file = request.files['image']
    if file.filename == '':
        return jsonify({"message": "No selected file!"}), 400

    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(filepath)
        image_path = 'uploads/' + filename

    data = request.form

    #data = request.get_json()

    # project_id = data['project_id']
    project_title = data['project_title']
    description = data['description']
    # 실제로는 세션에서 얻어옴
    creator_id = "test id"  # data['creator_id']
    recruitment_status = data['recruitment_status']
    recruitment_count = data['recruitment_count']
    # 이미지 어떻게 구현?
    # image_path = data.get('image_path', '')
    # 현재 날짜 가져옴
    creation_date = datetime.today()
    # 추후에 합격자 아이디 추가해
    accepted_ids = 'kim123 12311'  # data.get('accepted_ids', '')  # 합격자 아이디는 옵셔널

    conn = get_db()
    cursor = conn.cursor()

    # 다음 project_id 생성 최댓값 가져와서 +1
    try:
        cursor.execute("SELECT MAX(project_id) FROM projects_recruitment")
        row = cursor.fetchone()
        project_id = row[0] if row and row[0] else 0
        project_id += 1
    except:
        project_id = 1

    cursor.execute("""
        INSERT INTO projects_recruitment (project_id, project_title, description, creator_id, recruitment_status, recruitment_count, image_path, creation_date, accepted_ids)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """, (project_id, project_title, description, creator_id, recruitment_status, recruitment_count, image_path, creation_date, accepted_ids))

    conn.commit()
    conn.close()

    return jsonify({"message": "Project created successfully!"}), 202


# 프로젝트 등록(관리자) api
@app.route('/create_project_admin', methods=['POST'])
def create_project_admin():
    # 이미지 업로드
    if 'image' not in request.files:
        return jsonify({"message": "Image not found!"}), 400

    file = request.files['image']
    if file.filename == '':
        return jsonify({"message": "No selected file!"}), 400

    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
        file.save(filepath)
        image_path = filepath

    data = request.form
    #data = request.get_json()

    # project_id = data['project_id']
    project_title = data['project_title']
    description = data['description']
    project_creation_id = data['project_creation_id']
    # 실제로는 세션에서 얻어옴
    admin_id = "test id"  # data['admin_id']
    # 이미지 어떻게 구현?
    # image_path = data.get('image_path', '')
    # dd
    git_address = data['git_address']
    # 현재 날짜 가져옴
    sharing_date = datetime.today()

    conn = get_db()
    cursor = conn.cursor()

    # 다음 project_id 생성 최댓값 가져와서 +1
    try:
        cursor.execute("SELECT MAX(project_id) FROM projects_sign_up")
        row = cursor.fetchone()
        project_id = row[0] if row and row[0] else 0
        project_id += 1
    except:
        project_id = 1

    cursor.execute("""
        INSERT INTO projects_sign_up (project_id, project_title, description, project_creation_id, admin_id, image_path, git_address, sharing_date)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """, (project_id, project_title, description, project_creation_id, admin_id, image_path, git_address, sharing_date))

    conn.commit()
    conn.close()

    return jsonify({"message": "Project created successfully!"}), 203


#프로젝트 리스트 업
@app.route('/projects_list')
def projects_list():
    conn = get_db()
    cursor = conn.cursor()
    cursor.execute("""SELECT * FROM projects_recruitment""")
    projects = cursor.fetchall()
    conn.close()

    projects_list = [dict((cursor.description[i][0], value)
                          for i, value in enumerate(row)) for row in projects]
    return jsonify(projects_list)


#프로젝트 검색기능
@app.route('/search_project')
def search_project():
    query = request.args.get("query")
    conn = get_db()
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM projects_recruitment WHERE project_title LIKE ?", ('%' + query + '%',))
    projects = cursor.fetchall()
    #print(projects)
    return render_template('projects_list.html', projects=projects)

# md파일 불러오기

@app.route('/markdown_page')
def render_markdown():
    #추후에 db 전체를 가졔와서 for 문 돌림    ( FROM git_hub_address )
    url = "https://raw.githubusercontent.com/pytorch/vision/main/README.md"

    response = requests.get(url)

    if response.status_code == 200:
        content = response.text
        html_content = markdown(content)
        return html_content
    else:
        return "Error fetching markdown from GitHub", 500


if __name__ == '__main__':
    app.run(debug=True, port=5002)
