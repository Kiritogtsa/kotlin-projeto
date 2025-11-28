import { useState } from 'react';
import './login.css';
import { Userprovider, useUser } from '../contextos/usercontexto';
import { StatusEnum } from '../../model/enums/statusenum';

function Login() {
    const { user, updateUserPartial} = useUser();
    const apiUser = {
        nome: "Maria",
        email: "maria@email.com",
        status: StatusEnum.ATIVO
    };
    const handlesubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        let form = event.currentTarget;
        let formelement = new FormData(form)
        let inputindetifier: string = formelement.get("emailorname")?.toString() || "";
        let password: string = formelement.get("password")?.toString() || "";
        if (inputindetifier.match("@") && (inputindetifier.length > 10 && inputindetifier.length < 40)) {
            console.log("é um email valido")
        } else if (inputindetifier.match("@")) {
            console.log("é um email invalido")
        }
        updateUserPartial(apiUser)
    }
    return (
        <div className="loginform" >
            <form className="form_login" onSubmit={handlesubmit}>
                <label htmlFor="emailorname">email or name:</label>
                <input id="emailorname"
                    name="emailorname"
                    type="text"
                    minLength={5}
                    maxLength={25}
                    placeholder="email or name"
                    required
                />
                <label htmlFor="password">password:</label>
                <input id="password" name="password" minLength={3} maxLength={20} placeholder="password here" required />
                <button type="submit">Submit</button>
            </form>
        </div>
    )
}

export default Login;