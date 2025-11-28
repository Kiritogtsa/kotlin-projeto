import './App.css'
import './pages/login/login'
import './pages/contextos/usercontexto'
import { useState } from 'react';
import { Userprovider } from './pages/contextos/usercontexto';
import Home from './pages/home/home';
function App() {
   return (
    <Userprovider>
      <Home></Home>
    </Userprovider>
  )
}

export default App
