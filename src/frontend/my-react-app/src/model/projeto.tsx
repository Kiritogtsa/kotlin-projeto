import { TypeProjectEnum } from "./enums/typeproject";
import { StatusPJEnum } from "./enums/statuspj";
export interface Projeto {
    nome?: string;
    tipo?: TypeProjectEnum;
    status?: StatusPJEnum;
    progress?: number;
    descrction?: string;
}