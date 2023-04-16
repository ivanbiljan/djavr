import { Component, Input, OnInit } from '@angular/core';
import { StudentDto, StudentServiceService } from '../student-service.service';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.scss']
})
export class StudentDetailsComponent implements OnInit {
  student: StudentDto | null = null;

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
      });
  }
}
