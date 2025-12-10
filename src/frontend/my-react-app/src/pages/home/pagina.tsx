import { type JSX, useState } from "react";
import type { Tela } from "../../types/typestelas.ts";
import { useUser } from "../contextos/usercontexto.tsx";
import Login from "../login/login.tsx";
import Pagina2 from "./pagina2.tsx";

// por hoje e so isso
// amanha eu continuo com o basico do front end, e depois eu deixo a aparencia como é para ficar, pelos um pouco igual

function Pagina() {
  const { user } = useUser();
  const [telaAtual, setTelaAtual] = useState<Tela>("login");
  const mudarTela = (novaTela: Tela) => {
    setTelaAtual(novaTela);
  };
  const telas: Record<Tela, JSX.Element> = {
    login: <Login mudarTela={mudarTela} />,
    pagina2: <Pagina2 mudarTela={mudarTela} />,
  };

  return <div>{user ? telas[telaAtual] : telas["login"]}</div>;
}

export default Pagina;
