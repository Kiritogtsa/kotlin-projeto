import React, { createContext, useState, useContext } from "react";
import type { Colaborador } from "../../model/colaborador";
import { StatusEnum } from "../../model/enums/statusenum";
interface colaboradorcontext {
    user: Colaborador,
    setuserField: <K extends keyof Colaborador>(key: K, value: Colaborador[K]) => void,
    updateUserPartial:(partialUser:Partial<Colaborador>) =>void;
}

const Usercontext = createContext<colaboradorcontext | undefined>(undefined);

export const Userprovider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setuser] = useState<Colaborador>({
        nome: "",
        email: "",
        cpf: "",
        ramal: "",
        status: StatusEnum.PENDENTE,
        setor: "",
        habilidades: ""
    })
    const setuserField = <K extends keyof Colaborador>(key: K, value: Colaborador[K]) => {
        setuser(prev => ({ ...prev, [key]: value }));
    };
    const updateUserPartial = (partialUser:Partial<Colaborador>) =>{
    Object.entries(partialUser).forEach(([key, value]) => {
            setuserField(key as keyof Colaborador,value)
        });
    };
    return <Usercontext.Provider value={{ user, setuserField, updateUserPartial}}>{children}</Usercontext.Provider>
}
export const useUser = () => {
    const context = useContext(Usercontext);
    if (!context) throw new Error('useUser deve ser usado dentro de UserProvider');
    return context
}