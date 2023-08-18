from flask import Flask, jsonify, render_template, request
import sqlite3
from datetime import datetime

app = Flask(__name__)

DATABASE = 'users.db'


@app.route('/')
def index():
    return render_template('Sign_up.html') #Sign_up


#test api
@app.route('/api/greet', methods=['GET'])
def greet():
    return jsonify({"message": "test 한글?"})


# 데이터베이스 연결 함수
def get_db():
    conn = sqlite3.connect(DATABASE)
    return conn


#회원가입 api
@app.route('/sign_up', methods=['POST'])
def sign_up():
    data = request.get_json()

    id = data['id']
    password = data['password']
    name = data['name']
    nickname = data['nickname']
    email = data['email']
    git_address = data['git_address']

    conn = get_db()
    cursor = conn.cursor()

    # 중복된 아이디 체크
    cursor.execute("SELECT * FROM users WHERE id=?", (id,))
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


@app.route('/check_userid', methods=['POST'])
def check_userid():
    userid = request.json.get('userId')
    # DB에서 해당 userid 확인
    # 예: user = db.find_one({"userId": userid})
    # if user:
    #     return jsonify(available=False)
    return jsonify(available=True)


#프로젝트 모집 글(사용자) api
@app.route('/create_project', methods=['POST'])
def create_project():
    data = request.get_json()

    #project_id = data['project_id']
    project_title = data['project_title']
    description = data['description']
    # 실제로는 세션에서 얻어옴
    creator_id = "test id" #data['creator_id']
    recruitment_status = data['recruitment_status']
    recruitment_count = data['recruitment_count']
    # 이미지 어떻게 구현?
    #image_path = data.get('image_path', '')
    #현재 날짜 가져옴
    creation_date = datetime.today()
    # 추후에 합격자 아이디 추가해
    accepted_ids = 'kim123 12311'#data.get('accepted_ids', '')  # 합격자 아이디는 옵셔널

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
        INSERT INTO projects_recruitment (project_id, project_title, description, creator_id, recruitment_status, recruitment_count, creation_date, accepted_ids)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """, (project_id, project_title, description, creator_id, recruitment_status, recruitment_count, creation_date, accepted_ids))

    conn.commit()
    conn.close()

    return jsonify({"message": "Project created successfully!"}), 202


if __name__ == '__main__':
    app.run(debug=True)
