import { Categoria } from "./Categoria"
import { Usuario } from "./Usuario"

export class Produto {
    public id: number
    public nome: string
    public quantidade: string
    public valorTotal: string
    public codigo: string
    public categoria: Categoria
    public usuario: Usuario
}