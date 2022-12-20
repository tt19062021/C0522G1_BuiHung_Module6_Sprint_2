import {IBeer} from './i-beer';
import {ICustomer} from './i-customer';

export interface IOrderDetail {
  beerId?: IBeer;
  customerId?: ICustomer;
}
