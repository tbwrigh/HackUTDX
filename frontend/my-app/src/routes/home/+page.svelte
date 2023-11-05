<!-- form page -->
<script>
    import { onMount } from 'svelte';

    let genderValue = 5; // Default value
    let orientationValue = 5;
    let raceValue = 5;


    onMount(() => {
        const genderRange = document.getElementById('gender-range');
        const orientationRange = document.getElementById('orientation-range');
        const raceRange = document.getElementById('race-range');
        
        genderRange.addEventListener('input', (e) => {
            genderValue = parseInt(e.target.value, 10);
        });

        orientationRange.addEventListener('input', (e) => {
            orientationValue = parseInt(e.target.value, 10);
        });

        raceRange.addEventListener('input', (e) => {
            raceValue = parseInt(e.target.value, 10);
        });
    });
</script>

<div class="min-h-screen flex flex-col items-center justify-center bg-gradient-to-r from-red-500 via-yellow-500 via-green-500 via-blue-500 via-indigo-500 to-purple-500">
    <div class="bg-gray-50 h-96 rounded-lg max-w-xl w-full mt-10">
        <h1 class="font-bold text-1xl mb-6">
            On a scale of 1-10 with 1 being the lowest and 10 being the highest, please select how important each of the categories are to you when choosing companies to invest in:
        </h1>
        <div class="flex flex-col items-center justify-center">
            <!-- gender -->
            <label for="gender-range" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Gender </label>
            <input id="gender-range" type="range" min="0" max="10" value="5" step="1" class="w-80 h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700">
            <div id="gender-value" class="text-gray-900 dark:text-white mt-2">{genderValue}</div>
            <div class="my-1"></div>
            
            <!-- sexual orientation -->
            <label for="orientation-range" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Sexual Orientation </label>
            <input id="orientation-range" type="range" min="0" max="10" value="5" step="1" class="w-80 h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700">
            <div id="orientation-value" class="text-gray-900 dark:text-white mt-2">{orientationValue}</div>
            <div class="my-1"></div>
            
            <!-- race/ethnicity -->
            <label for="race-range" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Race/Ethnicity: </label>
            <input id="race-range" type="range" min="0" max="10" value="5" step="1" class="w-80 h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700">
            <div id="race-value" class="text-gray-900 dark:text-white mt-2">{raceValue}</div>
            <div class="my-1"></div>
            <a href="/callback">
                <button class="bg-blue-500 py-1 px-4 rounded m-3">
                    Submit
                </button>
            </a>
        </div>
    </div>
</div>