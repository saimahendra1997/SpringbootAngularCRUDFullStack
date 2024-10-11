import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrl: './employee-details.component.css'
})
export class EmployeeDetailsComponent implements OnInit{

        id?: number;
        employee?: Employee;
  
        constructor(private route: ActivatedRoute, private employeeService: EmployeeService){}

        // Using the employee-service there we write a method getEmployeeById() we are using that.
        ngOnInit(): void {
          // To retrieve the ID for that we are using activated route to get the ID from the route
          this.id = this.route.snapshot.params['id'];
          if (this.id !== undefined)
          {
          this.employee = new Employee();
          this.employeeService.getEmployeeById(this.id).subscribe(data =>
          {
              this.employee=data;
          });
        }
      }

}
