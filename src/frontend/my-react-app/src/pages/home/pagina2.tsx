import { type JSX, useState } from "react";
import type { dashboardtype } from "../../types/dashborad";
import Bar from "./componetes/bar";
import Home from "./layouthome/home";
import LayoutTarefa from "./layouttarefa/layouttarefa";
import type { Pages } from "../../utils/interfaces";
import "./pagina.css";
function Pagina2({ mudarTela }: Pages) {
    const [layoutatual, setLayoutAtual] = useState<dashboardtype>("home");
    const mudarLayout = (novoLayout: dashboardtype) => {
        setLayoutAtual(novoLayout);
    };

    const laouts: Record<dashboardtype, JSX.Element> = {
        home: <Home mudarlayout={mudarLayout} />,
        chamados: <></>,
        projetos: <></>,
        tarefas: <LayoutTarefa mudarlayout={setLayoutAtual} />,
        relatorios: <></>,
        equipe: <></>,
        configurações: <></>,
    };

    return (
        <div className="dashboard-container">
            <div className="dashboard-sidebar">
                <Bar mudarTela={mudarTela} mudarlayout={setLayoutAtual} />
            </div>

            <div className="dashboard-content">{laouts[layoutatual]}</div>
        </div>
    );
}

export default Pagina2;

