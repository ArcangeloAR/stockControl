import { Produto } from "./Produto"

export class Usuario {
    public id: number
    public nome: string
    public usuario: string
    public senha: string
    public tipo: string
    public foto: string
    public produto: Produto[]
}