import { useUser } from "../../contextos/usercontexto.tsx";

function LayoutTarefa() {
  const { user } = useUser();
  return <p>{user.cargo}</p>;
}

export default LayoutTarefa;
