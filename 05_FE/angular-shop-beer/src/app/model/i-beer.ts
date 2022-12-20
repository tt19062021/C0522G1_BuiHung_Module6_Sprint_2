import {IBeerType} from './i-beer-type';
import {IOrigin} from './i-origin';
import {IBrand} from './i-brand';

export interface IBeer {
  id: number;
  name: string;
  image: string;
  alcohol: number;
  volume: number;
  detail: string;
  priceOld: number;
  priceNew: number;
  beerExp: number;
  beerTypeName: IBeerType;
  originName: IOrigin;
  brandName: IBrand;
}
