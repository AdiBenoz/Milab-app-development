const express = require('express');
const fs = require('fs');
let app = express();


app.get('/tasks', (req, res) => {
    let printedTasks;
    fs.readFile('tasks.json', (err,content) =>{
        if(err) {
            console.error(err);
            return;
        }
        printedTasks = JSON.parse(content);
        if(Object.keys(printedTasks).length === 0){
            res.send("No tasks for you. Go to the beach");
        } else {
            res.send("Hello there! here are your current tasks: " + JSON.stringify(printedTasks));
        }
    })
});

app.post('/new', (req, res) => {
    let id = req.query.id;
    let newTask = req.query.task;
    if (!id || !newTask){
        res.send("please  id and task");
        return;
    }
    let tasksJson;
    let updatedTaskList;
    fs.readFile('tasks.json', (err,content) =>{
        if(err) {
            console.error(err);
            res.send(err + "try again");
            return;
        }
        tasksJson = JSON.parse(content);
        tasksJson[id] = newTask;
        updatedTaskList = JSON.stringify(tasksJson);
        fs.writeFile('tasks.json', updatedTaskList, (err,content) =>{
            if(err) {
                console.error(err);
                res.send(err + "try again");
                return;
            } else {
                res.send(`Task ${newTask} Added succesfully!`);
            }});
    });
});

app.post('/remove', (req, res) => {
    let id = req.query.id
    if (!id){
        res.send("please enter id");
        return;
    }
    let deletedTask;
    let tasksJson;
    let updatedTaskList;
    fs.readFile('tasks.json', (err,content) =>{
        if(err) {
            console.error(err);
            res.send(err + "try again");
            return;
        }
        tasksJson = JSON.parse(content);
        deletedTask = tasksJson[id];
        delete tasksJson[id];
        updatedTaskList = JSON.stringify(tasksJson);
        fs.writeFile('tasks.json', updatedTaskList, (err,content) =>{
            if(err) {
                console.error(err);
            res.send(err + "try again");
            } else {
                res.send(`Task ${deletedTask} Removed succesfully!`);
            }});
    });


});

app.listen(3000, ()=>{
    console.log('Listening on port 3000....');
});
