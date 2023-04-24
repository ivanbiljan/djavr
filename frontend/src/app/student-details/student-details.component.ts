import { Component, Input, OnInit } from '@angular/core';
import { CourseDto, GetStudentAddressResponse, GetStudentDetailsResponse, StudentDto, StudentServiceService } from '../student-service.service';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.scss']
})
export class StudentDetailsComponent implements OnInit {
  student: GetStudentDetailsResponse | null = null;
  address: GetStudentAddressResponse | null = null;
  courses: CourseDto[] = [];

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentServiceService
  ) {
  }

  ngOnInit(): void {
      this.route.paramMap.subscribe((params: ParamMap) => {
        const jmbag: string | null = params.get("jmbag");
        if (!jmbag) {
          return;
        }

        this.studentService.getStudentById(jmbag).subscribe(res => this.student = res);
        this.studentService.getStudentAddress(jmbag).subscribe(res => this.address = res);
        this.studentService.getEnrolledCourses(jmbag).subscribe(res => this.courses = res);
      });
  }
}
