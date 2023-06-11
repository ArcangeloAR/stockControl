import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/model/Categoria';
import { CategoriaService } from 'src/app/service/categoria.service';
import { environment } from 'src/environments/environment.prod';

@Component({
  selector: 'app-categoria-delete',
  templateUrl: './categoria-delete.component.html',
  styleUrls: ['./categoria-delete.component.css']
})
export class CategoriaDeleteComponent implements OnInit {

  categoria: Categoria = new Categoria()
  idCategoria: number

  constructor(
    private router: Router,
    private categoriaService: CategoriaService,
    private route: ActivatedRoute
    ) { }

  ngOnInit() { 
    window.scroll(0,0)
    if(environment.token == '') {
      this.router.navigate(["/entrar"])
      alert("Sua sessão expirou. Por gentileza, faça o login novamente!")
    }

    this.idCategoria = this.route.snapshot.params['id']
    this.findByIdCategoria(this.idCategoria)
  }

  findByIdCategoria(id: number) {
    this.categoriaService.getByIdCategoria(id).subscribe((resp: Categoria) => {
      this.categoria = resp
    })
  }

  deletar() {
    let verificaLenghEAutorizacao = false
    if(this.categoria.produto.length > 0) {
      if(environment.tipo != 'administrador') {
        alert("A categoria que está tentando ser deletada encontra-se com itens. Para deleta-la, é necessário que seja um usuário Administrador ou que a lista de produtos esteja vazia!")
      } else {
        verificaLenghEAutorizacao = true
      }
    } else {
      verificaLenghEAutorizacao = true
    }
    if(verificaLenghEAutorizacao) {
      this.categoriaService.deleteCategoria(this.idCategoria).subscribe(() => {
        alert("Categoria deletada com sucesso!")
        this.router.navigate(["/categorias"])
      })
    }
  }

}
