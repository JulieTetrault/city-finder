import * as React from 'react'
import cityImg from '../city.png'
import { SearchedCity } from "../models/searchedCity";

export const ResultList: React.FunctionComponent<{ cities: SearchedCity[] }> = ({ cities }) => {
    return <div className="ResultList">
        <ol>
            {cities.map((c) => <li key={c.id}>
                <img src={cityImg} alt="City logo"/>
                <span>Country: {c.name}</span>
                <span>Name: {c.ascii}</span>
            </li>)}
        </ol>
    </div>
}
