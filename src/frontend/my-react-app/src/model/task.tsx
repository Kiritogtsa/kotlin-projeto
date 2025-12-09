import type { Projeto } from "./projeto";
import type { Colaborador } from "./colaborador";
import  { StatusTsEnum } from "./enums/statusTs";
export interface Task{
    id: string | undefined;
    title: string,
    project: Projeto, 
    prazo : string,
    status: StatusTsEnum,
    responsavel: Colaborador 
};