import React, { useEffect, useState } from 'react';
import api from '../api';

const TaskList = () => {
    const [tasks, setTasks] = useState([]);
    const userEmail = 'user@example.com';

    useEffect(() => {
        const fetchTasks = async () => {
            try {
                const response = await api.get(`/tasks/getAllTasksByUser/${userEmail}`);
                setTasks(response.data.content);
            } catch (error) {
                console.error('Error fetching tasks', error);
            }
        };

        fetchTasks();

    }, [userEmail]);

    return (
        <div>
            <h2>Task List</h2>
            <ul>
                {tasks.map((task) => (
                    <li key={task.id}>{task.name}</li>
                ))}
            </ul>
        </div>
    );
};

export default TaskList;
