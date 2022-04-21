const ctx1 = document.getElementById('myChart1').getContext('2d');
const DATA_COUNT1 = 24; 
const labels1 = [];
for (let i = 0; i < DATA_COUNT1; ++i) {
  labels1.push(i.toString());
}
const myChart1 = new Chart(ctx1, {
    type: 'line',
    data: {
        labels: labels1,
        datasets: [{
            label: 'Price ( Past 1 Days ) in INR',
            data: [1132145, 3132421, 33123123, 51231231, 2, 3,3,23423423,3,4,234,23,423,2,23423,43242,23423423,42342,2312,312,3,123,12,3123,3123],
            borderColor: [
                '#ffd700'
            ],
            borderWidth: 4
        }]
    }
});

const ctx2 = document.getElementById('myChart2').getContext('2d');
const DATA_COUNT2 = 24; 
const labels2 = [];
for (let i = 0; i < DATA_COUNT2; ++i) {
  labels2.push(i.toString());
}
const myChart2 = new Chart(ctx2, {
    type: 'line',
    data: {
        labels: labels2,
        datasets: [{
            label: 'Price ( Past 30 Days ) in INR',
            data: [1132145, 3132421, 33123123, 51231231, 2, 3,3,2323423,3,4,234,23,423,2,23423,43242,23423423,42342,2312,312,3,123,12,3123,3123],
            borderColor: [
                '#ffd700'
            ],
            borderWidth: 4
        }]
    }
});

const ctx3 = document.getElementById('myChart3').getContext('2d');
const DATA_COUNT3 = 24; 
const labels3 = [];
for (let i = 0; i < DATA_COUNT2; ++i) {
  labels2.push(i.toString());
}
const myChart3 = new Chart(ctx3, {
    type: 'line',
    data: {
        labels: labels2,
        datasets: [{
            label: 'Price ( Past 90 Days ) in INR',
            data: [1132145, 3132421, 33123123, 51231231,423,42,34,23,4,22312313,4,32,342,1223123,12312,312,314,234,23,4,232,32423233,3,2323423,3,4,234,23,423,2,23423,43242,212,3123,3123],
            borderColor: [
                '#ffd700'
            ],
            borderWidth: 4
        }]
    }
});

const ctx4 = document.getElementById('myChart4').getContext('2d');
const DATA_COUNT4 = 24; 
const labels4 = [];
for (let i = 0; i < DATA_COUNT1; ++i) {
  labels4.push(i.toString());
}
const myChart4 = new Chart(ctx4, {
    type: 'line',
    data: {
        labels: labels4,
        datasets: [{
            label: 'Price ( Past 365 Days ) in INR',
            data: [1132145, 3132421, 33123123, 51231231, 2, 3,3,23423423,3,4,234,23,423,2,23423,43242,23423423,42342,2312,312,3,123,12,3123,3123],
            borderColor: [
                '#ffd700'
            ],
            borderWidth: 4
        }]
    }
});



