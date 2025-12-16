import React, { useEffect, useState } from "react";
import type { Projeto } from "../../../model/projeto.tsx";
import { fetchAny } from "../../../utils/getapi.tsx";

function LayoutProjeto() {
  const apikey = import.meta.env.VITE_API_URL_REACT;
  const [projects, setProjetos] = useState<Projeto[]>([]);
  const buttoneventlistner = async (
    event: React.MouseEvent<HTMLDivElement>,
  ) => {
    // nisto eu vou chamar um componete para recolher as informações obre o
    // projeto + os colaboradores deles, mesmo para não admin, desde que faça
    // parte do projeto, embora o unico que vai modificar vai ser o admin
    // em outro componete, não deste
  };
  useEffect(() => {
    async function loadprojetos() {
      const projetos = await fetchAny<Projeto[]>(apikey + "project/me");
      setProjetos(projetos);
    }
    loadprojetos();
  }, []);
  return (
    <div>
      {projects.map((projeto) => (
        <div id={projeto.id} onClick={buttoneventlistner}>
          <p>{projeto.nome}</p>
          <p>{projeto.descrction}</p>
          <p>{projeto.status}</p>
          <p>{projeto.tipo}</p>
        </div>
      ))}
    </div>
  );
}
export default LayoutProjeto;
