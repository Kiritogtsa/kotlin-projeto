import { useEffect, useState } from "react";
import type { Projeto } from "../../../model/projeto.tsx";
import type { Task } from "../../../model/task.tsx";
import { fetchAny } from "../../../utils/getapi.tsx";
import { useUser } from "../../contextos/usercontexto.tsx";
import "./home.css";
import ComponeteProjeto from "./projetocomponetes/componeteprojeto.tsx";
import Componetetask from "./taskcomponetes/taskcomponetes.tsx";

function Home() {
  const apikey = import.meta.env.VITE_API_URL_REACT;
  // estado global centralizado do usuario
  const { user } = useUser();
  // estado local para tasks e projetos
  const [tasks, setTasks] = useState<Task[]>([]);
  const [projetos, setProjetos] = useState<Projeto[]>([]);
  // event: React.MouseEvent<HTMLButtonElement>

  // carrego tasks e projetos ao montar os componetes
  useEffect(() => {
    async function loadtarefas() {
      const tarefas = await fetchAny<Task[]>(apikey + "tasks");
      setTasks(tarefas);
    }
    async function loadprojetos() {
      const projetos = await fetchAny<Projeto[]>(apikey + "project");
      setProjetos(projetos);
    }
    loadtarefas();
    loadprojetos();
  }, []);

  return (
    <div className="home">
      <p>{user.nome}</p>
      <Componetetask tasks={tasks} />
      <ComponeteProjeto projetos={projetos} />
    </div>
  );
}

export default Home;
