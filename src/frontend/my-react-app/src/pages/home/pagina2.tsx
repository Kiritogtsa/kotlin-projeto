// deno-lint-ignore-file
import { type JSX, useState } from "react";
import type { dashboardtype } from "../../types/dashborad.ts";
import type { Pages } from "../../utils/interfaces.ts";
import Bar from "./componetes/bar.tsx";
import Home from "./layouthome/home.tsx";
import LayoutProjeto from "./layoutprojeto/layoytprojeto.tsx";
import LayoutTarefa from "./layouttarefa/layouttarefa.tsx";
import "./pagina.css";

function Pagina2({ mudarTela }: Pages) {
  const [layoutatual, setLayoutAtual] = useState<dashboardtype>("home");
  const mudarLayout = (novoLayout: dashboardtype) => {
    setLayoutAtual(novoLayout);
  };

  const laouts: Record<dashboardtype, JSX.Element> = {
    home: <Home />,
    chamados: <></>,
    projetos: <LayoutProjeto />,
    tarefas: <LayoutTarefa />,
    relatorios: <></>,
    equipe: <></>,
    configurações: <></>,
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-sidebar">
        <Bar mudarlayout={setLayoutAtual} />
      </div>

      <div className="dashboard-content">{laouts[layoutatual]}</div>
    </div>
  );
}

export default Pagina2;
