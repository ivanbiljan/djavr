import { Component, OnInit } from '@angular/core';
import { Student, StudentServiceService } from '../student-service.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent implements OnInit {
  students: Student[] = [];
  selectedStudent: Student | null = null;

  constructor(private studentService: StudentServiceService) {
  }

  ngOnInit(): void {
      this.getStudents();
  }

  getStudents(): void {
    this.studentService.getStudents().subscribe(students => this.students = students);
  }

  onSelect(student: Student): void {
    this.selectedStudent = student;
  }
}
