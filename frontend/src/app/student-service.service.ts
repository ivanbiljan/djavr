import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

export interface Student {
  jmbag: string;
  numberOfEcts: number;
  hasToPayTuition: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {

  private students: Student[] = [
    {jmbag: "0246096864", numberOfEcts: 180, hasToPayTuition: false},
    {jmbag: "0246096865", numberOfEcts: 60, hasToPayTuition: true},
    {jmbag: "0246096866", numberOfEcts: 180, hasToPayTuition: false},
  ];

  constructor() { }

  getStudents(): Observable<Student[]> {
    return of(this.students);
  }
}
