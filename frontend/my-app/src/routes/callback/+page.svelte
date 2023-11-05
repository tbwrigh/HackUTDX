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

    let data = [];

    // get data from localhost:5174 
    onMount(async () => {
        const res = await fetch(`http://localhost:5174/recommendation/top/12/weights/${genderVal}/${orientationVal}/${raceVal}`);
        data = await res.json();
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
            Hello! 
        </h1>


        <a href="/search">
            <button class="bg-blue-500 py-5 px-5 rounded m-3 text-white">
                Search
            </button>
        </a>

    </div>
    <div class="flex flex-col bg-lime-50">
        <div class="flex justify-between items-center m-4">
            <h1 class="font-bold text-3xl">
                Here are the stocks we recommend for you: 
            </h1>
        </div>
    </div>


    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-5 h-screen p-5">

    {#each data as point}
    <div class="flex flex-col items-center justify-center h-auto mb-5 block max-w-sm p-10 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
        <h5 class="text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{point.companyName}</h5>
        <p class="font-normal text-gray-700 dark:text-gray-400">{point.sector}</p>
        <h3 class="font-normal text-gray-700 dark:text-gray-400">Stockticker: {point.stockTicker}</h3>
    </div>
    {/each}

    </div>
</div>