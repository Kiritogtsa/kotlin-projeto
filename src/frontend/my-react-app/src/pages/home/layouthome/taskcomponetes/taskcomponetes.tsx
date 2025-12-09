import type { Task } from "../../../../model/task";
import "./taskcomponetes.css";

interface TaskListProps {
    tasks: Task[];
}

// outro jeito, dependo do local eu vou usar o com interface e por ai
// {tasks}:{tasks:Task[]}
function Componetetask({ tasks }: TaskListProps) {
    console.log(tasks);
    return (
        <div className="tasks">
            {tasks.map((task) => (
                <div id={task.id} className="task">
                    <p>{task.title}</p>
                    <p>{task.project.nome}</p>
                    <p>{task.responsavel.nome}</p>
                    <p>{task.status}</p>
                </div>
            ))}
        </div>
    );
}

export default Componetetask;

