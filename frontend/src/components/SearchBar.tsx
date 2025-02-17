import React, { useState, useRef, useEffect } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSearch, faCircleNotch } from '@fortawesome/free-solid-svg-icons'

import './SearchBar.css'
import {SearchedCity} from "../models/searchedCity";

export interface SearchBarProps {
    placeholder: string,
    onCitySearch: (cities: SearchedCity[]) => any
}

const SearchBar: React.FunctionComponent<SearchBarProps> = (props) => {
    const inputRef = useRef<HTMLInputElement>(null);
    const [cityName, setCityName] = useState('');
    const [cities, setCities] = useState<SearchedCity[]>([]);
    const [isLoading, setIsLoading] = useState(false);
    const [position, setPosition] = useState<GeolocationCoordinates | null>(null)

    const search = async () => {
        setIsLoading(true);
        await props.onCitySearch(cities)
        setIsLoading(false)
    }

    const onKeyDown = (e: any) => {
        if (e.key === 'Enter') {
            props.onCitySearch(cities);
            setCityName('');
        }
    }

    useEffect(() => {
        navigator.geolocation.getCurrentPosition((p) => {
            setPosition(p.coords);
        });
    }, [])

    useEffect(() => {
        if (cityName === "") {
            setCities([]);
            return;
        }

        const q = cityName.lowerCase()
        const path = position === null
            ? `http://localhost:8080/v2/cities/search?name=${q}&page=0`
            : `http://localhost:8080/v2/cities/search?name=${q}&latitude=${position.latitude}&longitude=${position.longitude}&page=0`

        try {
            fetch(path)
                .then(response => response.json())
                .then(({ cities }) => setCities(cities));
        } catch (e) {
            console.error(e);
        }

    }, [cityName, position])

    return <div className="SearchBar">
        <input
            ref={inputRef}
            placeholder={props.placeholder}
            value={cityName}
            onKeyDown={onKeyDown}
            onChange={(e) => setCityName(e.target.value)}></input>

        <button onClick={search}>
            <FontAwesomeIcon icon={!isLoading ? faSearch : faCircleNotch} className={isLoading ? 'fa-spin' : ''} />
        </button>

        <div className="suggestions">
            {cities.map((suggestion, key) => <div key={key} onClick={() => { setCityName(suggestion.ascii) }}>
                <span>{suggestion.ascii}</span>
            </div>)}
        </div>
    </div>
}

export default SearchBar;