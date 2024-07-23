import { Component, OnInit } from '@angular/core';
import { TodoService } from '../../todo.service';
import { Todo } from './todo';
import { ModalUpdateComponent } from '../modal-update/modal-update.component';
import { MatDialog } from '@angular/material/dialog';
@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html'
})

export class TodoListComponent implements OnInit {
  todos: Todo[] = [];
  newTodo: Partial<Todo> = { title: '' };
  displayedColumns: string[] = ['Status','id', 'title', 'completed', 'createdAt', 'actions'];

  constructor(private todoService: TodoService, public dialog: MatDialog) { }

  ngOnInit() {
    this.fetchTodos();
  }

  fetchTodos() {
    this.todoService.getTodos().subscribe(todos => {
      this.todos = todos;
    });
  }

  addTodo() {
    if (!this.newTodo.title) {
      return; 
    }


    const newTodo: Todo = {
      id: this.todos.length + 1, 
      title: this.newTodo.title!,
      completed: this.newTodo.completed || false,
      createdAt: new Date()
    };

    this.todoService.addTodo(newTodo).subscribe(todo => {
      this.fetchTodos();
      this.newTodo = { title: '' }; 
    });
  }

  updateTodo(updateTodo:any){
    this.todoService.updateTodo(updateTodo).subscribe(update=>{
      this.fetchTodos();
    })
    console.log(updateTodo);


  }
  deleteTodo(id: number) {
    this.todoService.deleteTodo(id).subscribe(() => {
      this.fetchTodos();
    });
  }
  
  openEditDialog(todo: Todo): void {
    const dialogRef = this.dialog.open(ModalUpdateComponent, {
      width: '400px',
      data: { title: todo.title }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.updateTodo({ ...todo, title: result.title });
      }
    });
  }

}
 
