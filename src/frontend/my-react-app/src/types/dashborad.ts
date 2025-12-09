export const dashboard = ["home","chamados", "projetos", "tarefas", "relatorios", "equipe", "configurações"] as const;

export type dashboardtype = typeof dashboard[number]; 