import type { Layoutchanges } from "../../../utils/interfaces";
import { useUser } from "../../contextos/usercontexto";

function LayoutTarefa( {mudarlayout}:Layoutchanges) {
   const {user} = useUser();
    return (
        <p>{user.cargo}</p>
    );
}

export default LayoutTarefa;