import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-truckinfo',
  templateUrl: './truckinfo.component.html',
  styleUrls: ['./truckinfo.component.css']
})
export class TruckinfoComponent implements OnInit {

  submitted = false;
  public fuel_required: number;
  public fuel_cost: number;
  public fstops: number;
  public tdist: number;
  public mil: number;
  public fl: number;
  public tcap: number;
  public rfuel: number;
  public fcount: number;
  form = new FormGroup({
    tdistance: new FormControl('', [Validators.required]),
    mileage: new FormControl('', [Validators.required]),
    fleft: new FormControl('', [Validators.required]),
    tcapacity: new FormControl('', [Validators.required])
  });
  get f()
  {
    return this.form.controls;
  }
  constructor() { }

  ngOnInit(): void {
  }
  onSubmit()
  {
    console.log(this.form.value);
    this.rfuel = this.tdist / this.mil;
    this.fuel_required = this.rfuel - this.fl;
    this.fuel_required.toFixed(2);
    this.fuel_cost = this.fuel_required * 65;
    this.fuel_cost.toFixed(1);
    this.fcount = this.tcap * this.mil;
    this.fstops = this.tdist / this.fcount;
    if (this.fstops < 1)
    {
        this.fstops = 1;
    }
    else{
      this.fstops += 1;
    }

    if (this.fuel_required === 0 || this.fuel_required < 0)
    {
       this.fstops = 0;
       this.fuel_required = 0;
       this.fuel_cost = 0;
    }
  }

}
