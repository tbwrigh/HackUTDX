<!-- home page -->
<script>
    import { onMount } from "svelte";
    import { withAuth } from "../auth";
    import { gender, sexualOrientation, race } from "../Store";


    let genderVal = 0;
    gender.subscribe(val => {genderVal = val})

    let orientationVal = 0;
    sexualOrientation.subscribe(val => {orientationVal = val})

    let raceVal = 0;
    race.subscribe(val => {raceVal = val})

    // get data from localhost:5174 
    onMount(async () => {
        const res = await fetch(`http://localhost:5174/recommendation/top/10/weights/${genderVal}/${orientationVal}/${raceVal}`);
        const data = await res.json();
        console.log(data);

        for (let element of data) {
            const subres = await fetch(`http://localhost:5174/stock/price/${element.ticker}`);
            const subdata = await subres.json();
            console.log(subdata);
        }
    });

</script>

<div class="min-h-screen flex flex-col bg-lime-50">
    <div class="flex justify-between items-center m-4">
        <h1 class="font-bold text-6xl">
            Hello User!
        </h1>
        <form class="flex items-center">   
            <label for="default-search" class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search</label>
                <div class="relative">
                    <div class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
                        <svg class="w-4 h-4 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                        </svg>
                    </div>
                    <input type="search" id="default-search" class="block w-full p-4 pl-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="Search Mockups, Logos..." required>
                    <button type="submit" class="text-white absolute right-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Search</button>
                </div>
        </form>
    </div>
    <h1 class="font-bold text-5xl my-14 m-4">
        Top Options:

        {genderVal} {orientationVal} {raceVal}
    </h1>
    <div class="flex flex-col items-center justify-center h-auto mb-5 block max-w-sm p-10 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
        <h5 class="text-2xl font-bold tracking-tight text-gray-900 dark:text-white">Company</h5>
        <p class="font-normal text-gray-700 dark:text-gray-400">Sector</p>
        <h3 class="font-normal text-gray-700 dark:text-gray-400">Stockticker: </h3>
    </div>
</div>

Company
Sector
Stockticker