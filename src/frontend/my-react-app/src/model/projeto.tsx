import { StatusPJEnum } from "./enums/statuspj.tsx";
import { TypeProjectEnum } from "./enums/typeproject.tsx";
export interface Projeto {
    id?: string | undefined;
    nome?: string;
    tipo?: TypeProjectEnum;
    status?: StatusPJEnum;
    progress?: number;
    descrction?: string;
}

