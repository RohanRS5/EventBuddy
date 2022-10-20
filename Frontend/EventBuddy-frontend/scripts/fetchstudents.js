async function getStudents() {
    let url = ' http://localhost:8001/events/eveId';
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
  }
  
  async function renderStudents() {
    let students = await getStudents();
    let html = '';
    students.forEach(students => {
        let htmlSegment = `<div class="col">
        <div class="card text-white bg-success my-4">
          <div class="card-body">
            <h5 class="card-title">${students.studentName}</h5>
            <p class="card-text">${students.studentDepartment} <br>
            <p class="card-text">${students.studentDivision} <br>
            </p>
          </div>
        </div>
      </div>`;
  
        html += htmlSegment;
    });
  
    let container = document.getElementById('student-grid');
    container.innerHTML = html;
  }
  
  renderStudents();