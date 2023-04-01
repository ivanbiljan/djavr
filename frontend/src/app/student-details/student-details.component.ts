import { Component, Input } from '@angular/core';
import { Student } from '../student-service.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.scss']
})
export class StudentDetailsComponent {
  @Input() student: Student | null = null;
}
