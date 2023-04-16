import { Component, OnInit } from '@angular/core';
import { StudentDto, StudentServiceService } from '../student-service.service';
import {DateTime} from 'luxon';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent implements OnInit {
  students: StudentDto[] = [];

  constructor(private studentService: StudentServiceService) {
  }

  ngOnInit(): void {
      this.getStudents();
  }

  getStudents(): void {
    this.studentService.getStudents().subscribe(students => this.students = students);
  }

  onSelect(student: StudentDto): void {
  }

  add(firstName: string, lastName: string, jmbag: string, numberOfECTS: number): void  {
    firstName = firstName.trim(); lastName = lastName.trim(); jmbag = jmbag.trim(); if (!firstName || !lastName || !jmbag || !numberOfECTS) { return; } 
    const dateOfBirth = this.randomDate(new Date(1990, 0, 1), new Date(2005, 0, 1));
    this.studentService.addStudent({ firstName, lastName, uid: jmbag, ectsPoints: numberOfECTS, dateOfBirth: DateTime.fromJSDate(dateOfBirth).toFormat("dd.MM.yyyy") }).subscribe(student => { this.students.push(student); });
  }

  delete(student: StudentDto) {
    const index = this.students.indexOf(student);
    this.studentService.deleteStudent({uid: student.uid}).subscribe(_ => this.students.splice(index, 1));
  }

  randomDate(start: Date, end: Date): Date {
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
  }
}
