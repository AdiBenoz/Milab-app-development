const express = require('express');
const fs = require('fs');
let app = express();



app.get('/tasks', (req, res) => {
    let printedTasks = printTasks();
    res.send(printedTasks);
});

app.post('/new', (req, res) => {
    let id = req.query.task
    let newTask = req.query.task;
    let status = addTask(id, newTask);
    res.send(status);
});

app.post('/remove', (req, res) => {
    let id = req.query.task
    let status = removeTask(id);
    res.send(status);
});



function printTasks() {
    let printedTasks;
    fs.readFile('tasks.json', (err,content) =>{
        if(err) {
            console.error(err);
            return;
        }
        printedTasks = content;
    })
    return printTasks;
}


function addTask(id, newTask) {
    let tasksJson;
    let updatedTaskList;
    fs.readFile('tasks.json', (err,content) =>{
        if(err) {
            console.error(err);
            return;
        }
        tasksJson = JSON.parse(content);
        tasksJson.tasks.id = newTask;
        updatedTaskList = JSON.stringify(tasksJson);
        fs.writeFile('tasks.json', updatedTaskList, (err,content) =>{
            if(err) {
                return err;
            } else {
                return "Task Added succesfully!"
            }});
    });
}

function removeTask(id) {
    let tasksJson;
    let updatedTaskList;
    fs.readFile('tasks.json', (err,content) =>{
        if(err) {
            console.error(err);
            return;
        }
        tasksJson = JSON.parse(content);
        delete tasksJson.tasks[id];
        updatedTaskList = JSON.stringify(tasksJson);
        fs.writeFile('tasks.json', updatedTaskList, (err,content) =>{
            if(err) {
                return err;
            } else {
                return "Task Added succesfully!"
            }});
    });
}