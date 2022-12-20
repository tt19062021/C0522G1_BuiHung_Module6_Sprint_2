import {IBeerDto} from '../dto/i-beer-dto';

export interface IImage {
  id?: number;
  name?: string;
  isDelete?: string;
  beer?: IBeerDto;
}
