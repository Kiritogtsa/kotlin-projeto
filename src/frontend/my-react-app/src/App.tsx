import "./App.css";
import "./pages/login/login";
import "./pages/contextos/usercontexto";
import { Userprovider } from "./pages/contextos/usercontexto";
import Pagina from "./pages/home/pagina";
function App() {
  return (
    <Userprovider>
      <Pagina />
    </Userprovider>
  );
}

export default App;
