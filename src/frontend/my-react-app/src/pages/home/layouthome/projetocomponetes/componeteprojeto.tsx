import type { Projeto } from "../../../../model/projeto.tsx";
import "./projetocomponetes.css";
interface ProjetosPropsList {
  projetos: Projeto[];
}

function ComponeteProjeto({ projetos }: ProjetosPropsList) {
  return (
    <div className="projetos">
      {projetos.map((projeto) => (
        <div id={projeto.id} className="projeto">
          <p>{projeto.nome}</p>
          <p>{projeto.descrction}</p>
          <p>{projeto.status}</p>
          <p>{projeto.progress}</p>
          <p>{projeto.tipo}</p>
        </div>
      ))}
    </div>
  );
}
export default ComponeteProjeto;
