import type { Tela } from "../types/typestelas"
import type { dashboardtype } from "../types/dashborad"
export interface Pages {
    mudarTela: (tela: Tela) => void
}
export interface Layoutchanges{
    mudarlayout :(dashboard:dashboardtype) =>void
}
export interface barProps extends Pages,Layoutchanges{};