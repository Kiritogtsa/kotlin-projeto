import Login from '../login/login'
import { Userprovider, useUser } from '../contextos/usercontexto';

// por hoje e so isso
// amanha eu continuo com o basico do front end, e depois eu deixo a aparencia como é para ficar, pelos um pouco igual
function Home() {
    const { user, setuserField } = useUser();
    const isloggin = user.nome !== "";
    return isloggin ?
        (<>
        </>) :
        (<Login>
        </Login>);
}
export default Home;