import { StatusEnum } from "./enums/statusenum";
export interface Colaborador{
    nome?: string,
    email?: string,
    cpf?: string,
    ramal?: string,
    status?: StatusEnum,
    cargo?: string,
    setor?: string,
    habilidades?: string
}