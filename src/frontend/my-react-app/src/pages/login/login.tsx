import { useEffect, useState } from "react";
import "./login.css";
import { useUser } from "../contextos/usercontexto";
import type { Tela } from "../../types/typestelas";
import "../../utils/auth";
import { fetchme } from "../../utils/auth";
import type { barProps, Pages } from "../../utils/interfaces";

interface LoginProps extends Pages {}

export default function Login({ mudarTela }: LoginProps) {
    const apikey = import.meta.env.VITE_API_URL_REACT;
    const { updateUserPartial } = useUser();
    useEffect(() => {
        async function loadUser() {
            const user = await fetchme(apikey + "me");
            if (user) {
                updateUserPartial(user);
                mudarTela("pagina2");
            }
        }
        loadUser();
    }, []);

    async function mandar_dados(email: string, password: string) {
        const body = { email, password };

        const response = await fetch(apikey + "login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include",
            body: JSON.stringify(body),
        });

        if (!response.ok) {
            throw new Error("Login falhou");
        }

        const data = await response.json();
        updateUserPartial(data); // agora funciona
    }

    const handlesubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const form = event.currentTarget;
        const formelement = new FormData(form);

        const inputidentifier = formelement.get("emailorname")?.toString() ||
            "";
        const password = formelement.get("password")?.toString() || "";

        await mandar_dados(inputidentifier, password);
        mudarTela("pagina2");
    };
    return (
        <div className="loginform">
            <form className="form_login" onSubmit={handlesubmit}>
                <label htmlFor="emailorname">Email or name:</label>
                <input
                    id="emailorname"
                    name="emailorname"
                    type="text"
                    minLength={5}
                    maxLength={25}
                    placeholder="email or name"
                    required
                />

                <label htmlFor="password">Password:</label>
                <input
                    id="password"
                    name="password"
                    type="password"
                    minLength={3}
                    maxLength={20}
                    placeholder="password here"
                    required
                />

                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

