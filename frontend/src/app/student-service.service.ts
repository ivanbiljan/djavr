import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

export interface StudentDto {
  uid: string;
  ectsPoints: number;
  hasToPayTuition: boolean;
}

export interface InsertStudentRequest {
  firstName: string;
  lastName: string;
  uid: string;
  ectsPoints: number;
  dateOfBirth: string;
}

export interface UpdateStudentRequest {
  firstName: string;
  lastName: string;
  uid: string;
  ectsPoints: number;
  dateOfBirth: Date;
}

export interface DeleteStudentRequest {
  uid: string;
}


@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {

  private readonly studentsUrl: string = "http://localhost:8080/students";
  private readonly httpOptions = {
    headers: new HttpHeaders({"Content-Type": "application/json"})
  }

  constructor(
    private httpClient: HttpClient
  ) { }

  getStudents(): Observable<StudentDto[]> {
    return this.httpClient.get<StudentDto[]>(this.studentsUrl);
  }

  getStudentById(uid: string): Observable<StudentDto> {
    return this.httpClient.get<StudentDto>(`${this.studentsUrl}/${uid}`);
  }

  addStudent(student: InsertStudentRequest): Observable<StudentDto> {
    return this.httpClient.post<StudentDto>(this.studentsUrl, student, this.httpOptions);
  }

  updateStudent(student: UpdateStudentRequest): Observable<any> {
    return this.httpClient.put(`${this.studentsUrl}/${student.uid}`, student, this.httpOptions);
  }

  deleteStudent(student: DeleteStudentRequest): Observable<any> {
    return this.httpClient.delete(`${this.studentsUrl}/${student.uid}`, this.httpOptions);
  }
}
