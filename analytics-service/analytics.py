from flask import Flask, jsonify
import urllib.request
import json

app = Flask(__name__)

GATEWAY_URL = "http://localhost:8080/api"

@app.route("/analytics/overview", methods=["GET"])
def overview():
    try:
        req_students = urllib.request.urlopen(f"{GATEWAY_URL}/students")
        students = json.loads(req_students.read().decode('utf-8'))
    except Exception as e:
        students = []

    try:
        req_courses = urllib.request.urlopen(f"{GATEWAY_URL}/courses")
        courses = json.loads(req_courses.read().decode('utf-8'))
    except Exception as e:
        courses = []

    return jsonify({
        "total_students": len(students),
        "total_courses": len(courses),
        "system_status": "ONLINE",
        "consumed_via_gateway": True
    })

if __name__ == "__main__":
    print("Starting Python Analytics Service on port 5000...")
    app.run(port=5000, debug=True)
