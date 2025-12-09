import "./home.css";
import { useUser } from "../../contextos/usercontexto";
import type { Layoutchanges } from "../../../utils/interfaces";
import { useEffect, useState } from "react";
import type { Task } from "../../../model/task";
import { fetchAny } from "../../../utils/getapi";
import Componetetask from "./taskcomponetes/taskcomponetes";

function Home({ mudarlayout }: Layoutchanges) {
    // aqui ainda vai adicionar mais coisas, falta o projetos, 
    const apikey = import.meta.env.VITE_API_URL_REACT;
    const { user } = useUser();
    const [tasks, setTasks] = useState<Task[]>([])
    const buttonevent = (event: React.MouseEvent<HTMLButtonElement>) => {
        mudarlayout("tarefas")
    }

    useEffect(() => {
        async function loadtarefas() {
            const tarefas = await fetchAny<Task[]>(apikey + "tasks");
            setTasks(tarefas)
        }
        loadtarefas()
    }, [])
    return (

        <div className="home">
            <p>{user.nome}</p>
            <Componetetask tasks={tasks}/>
            <button onClick={buttonevent}> teste</button>
        </div>
    )
}

export default Home;
