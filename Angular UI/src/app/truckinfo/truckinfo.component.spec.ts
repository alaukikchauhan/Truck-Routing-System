import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TruckinfoComponent } from './truckinfo.component';

describe('TruckinfoComponent', () => {
  let component: TruckinfoComponent;
  let fixture: ComponentFixture<TruckinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TruckinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TruckinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
