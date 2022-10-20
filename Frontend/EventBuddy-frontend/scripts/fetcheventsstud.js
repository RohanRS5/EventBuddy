async function getEvents() {
    let url = ' http://localhost:8001/events/';
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderEvents() {
    let events = await getEvents();
    let html = '';
    events.forEach(event => {
        let htmlSegment = `<div class="col">
        <div class="card text-white bg-success my-4">
          <div class="card-body">
            <h5 class="card-title">${event.eventName}</h5>
            <p class="card-text">${event.eventDescription} <br>
            <p class="card-text">${event.eventVenue} <br>
            <p class="card-text">${event.eventTime} <br>
            <p class="card-text">${event.eventDate} <br>
            </p>
            <a class="btn btn-outline-light rounded-pill"  style="background-color: #de5499" href="./StudentDashboard.html" role="button" onclick="alerthim()">Register</a>
            
          </div>
        </div>
      </div>`;

        html += htmlSegment;
    });

    let container = document.getElementById('event-grid');
    container.innerHTML = html;
}

renderEvents();