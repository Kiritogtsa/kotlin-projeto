import type { Projeto } from "./projeto";
import type { Colaborador } from "./colaborador";
import  { StatusTsEnum } from "./enums/statusTs";
export interface Task{
    title: string,
    project: Projeto, 
    prazo : string,
    status: StatusTsEnum,
    reponsavel: Colaborador 
};