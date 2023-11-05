<script>
    import { onMount } from "svelte";
    import { withAuth } from "../auth";
    import { gender, sexualOrientation, race } from "../Store";

    let genderValue = 5;

    let orientationValue = 5;

    let raceValue = 5;

    let totalValue = 15;


    let sectors = []
    let searchResults = []

    // get data from localhost:5174 
    onMount(async () => {
        let sectorReq = await fetch(`http://localhost:5174/sectors`);
        sectors = await sectorReq.json();

        const genderRange = document.getElementById('gender-min');
        const orientationRange = document.getElementById('orientation-min');
        const raceRange = document.getElementById('race-min');
        const totalRange = document.getElementById('total-min');
        const sectorDropdown = document.getElementById('sector');
        const submitButton = document.getElementById('submit');


        genderRange.addEventListener('input', (e) => {
            genderValue = parseInt(e.target.value, 10);
        });

        orientationRange.addEventListener('input', (e) => {
            orientationValue = parseInt(e.target.value, 10);
        });

        raceRange.addEventListener('input', (e) => {
            raceValue = parseInt(e.target.value, 10);
        });

        totalRange.addEventListener('input', (e) => {
            totalValue = parseInt(e.target.value, 10);
        });


        submitButton?.addEventListener('click', async (e) => {
            e.preventDefault();
            const options = sectorDropdown?.selectedOptions;
            var values = Array.from(options).map(({ value }) => value);

            let sectorString = values.join('&sector=');
            
            let searchReq = await fetch(`http://localhost:5174/companies/advanced?minGender=${genderValue}&minSexualOrientation=${orientationValue}&minRace=${raceValue}&minTotal=${totalValue}&sector=${sectorString}`);
            searchResults = await searchReq.json();
            console.log(searchResults);
        })


        // const res = await fetch(`http://localhost:5174/recommendation/top/10/weights/${genderVal}/${orientationVal}/${raceVal}`);
        // const data = await res.json();
        // console.log(data);

        // for (let element of data) {
        //     const subres = await fetch(`http://localhost:5174/stock/price/${element.ticker}`);
        //     const subdata = await subres.json();
        //     console.log(subdata);
        // }


    });

</script>

<aside id="default-sidebar" class="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0" aria-label="Sidebar">
    <div class="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
        <label for="gender-min">Min Gender Score:</label>
<input type="range" min="0" max="10" id="gender-min" name="gender-min">
<br>
<label for="orientation-min">Min Sexual Orientation Score:</label>
<input type="range" min="0" max="10" id="orientation-min" name="orientation-min">
<br>
<label for="race-min">Min Race Score:</label>
<input type="range" min="0" max="10" id="race-min" name="race-min">
<br>
<label for="total-min">Min Total Score:</label>
<input type="range" min="0" max="30" id="total-min" name="total-min">
<br>

<label for="sector">Sectors:</label>
<select name="sector" id="sector" multiple>
    {#each sectors as sector}
        <option value={sector}>{sector}</option>
    {/each}
</select>

<button id="submit">Submit</button>
     </div>
</aside>

<div class="p-4 sm:ml-64">
    <div>
        {#each searchResults as result}
            <div style="padding:20px;">
                <h1>{result.companyName}</h1>
                <h2>{result.stockTicker}</h2>
                <h3>{result.sector}</h3>
                <h4>{result.genderScore}</h4>
                <h4>{result.soScore}</h4>
                <h4>{result.raceScore}</h4>
                <h4>{result.totalScore}</h4>
            </div>
        {/each}
        
        </div>
</div>


