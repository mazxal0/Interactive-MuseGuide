"use client";

import { useState } from "react";
import {
    Button,
    DoorIcon,
    Header,
    TicketIcons,
    BankIcon,
    ToiletIcon,
    StairsIcon,
} from "@/components";
import { useRouter } from "next/navigation";

const floorLocations: Record<string, string[]> = {
    "0 этаж": [
        "Выставка 0 этажа",
        "Туалет",
        "Лестница",
    ],
    "1 этаж": [
        "Выставка 1 этажа",
        "Лестница",
    ],
    "2 этаж": [
        "Выставка 2 этажа",
        "Лестница",
    ],
};

const destinations = [
    "Вход",
    "Касса",
    "Выставка 0 этажа",
    "Выставка 1 этажа",
    "Выставка 2 этажа",
];

function getIcon(name: string) {
    const lower = name.toLowerCase();

    if (lower.includes("выставка")) {
        return <BankIcon />;
    }

    if (lower.includes("туалет")) {
        return <ToiletIcon />;
    }

    if (lower.includes("лестница")) {
        return <StairsIcon />;
    }

    if (lower.includes("вход")) {
        return <TicketIcons />;
    }

    if (lower.includes("касса")) {
        return <DoorIcon />;
    }

    return null;
}

export default function Path() {
    const [step, setStep] = useState(1);

    const [from, setFrom] = useState("");
    const [subLocation, setSubLocation] = useState("");
    const [to, setTo] = useState("");

    const router = useRouter();

    const updateQuery = (
        paramsToUpdate: Record<string, string>
    ) => {
        const params = new URLSearchParams(
            window.location.search
        );

        Object.entries(paramsToUpdate).forEach(
            ([key, value]) => {
                params.set(key, value);
            }
        );

        window.history.replaceState(
            {},
            "",
            `${window.location.pathname}?${params.toString()}`
        );
    };

    const selectFrom = (location: string) => {
        setFrom(location);
        updateQuery({ from: location });

        if (
            location === "0 этаж" ||
            location === "1 этаж" ||
            location === "2 этаж"
        ) {
            setStep(2);
            return;
        }

        setStep(3);
    };

    const selectSubLocation = (location: string) => {
        setSubLocation(location);
        updateQuery({ location });
        setStep(3);
    };

    const buildRoute = (destination: string) => {
        setTo(destination);
        updateQuery({ to: destination });
        setStep(4);
    };

    const resetRoute = () => {
        setStep(1);
        setFrom("");
        setSubLocation("");
        setTo("");

        window.history.replaceState(
            {},
            "",
            window.location.pathname
        );
    };

    return (
        <div>
            <Header
                headerText={
                    step === 1
                        ? "Где вы находитесь?"
                        : step === 2
                            ? "Уточните местоположение"
                            : step === 3
                                ? "Куда хотите попасть?"
                                : "Маршрут построен"
                }
            />

            <div className="flex flex-col mx-2 my-2 gap-4 text-left">
                {/* STEP 1 */}
                {step === 1 && (
                    <>
                        <Button
                            className="w-full flex justify-between items-center"
                            onClick={() =>
                                selectFrom("Вход")
                            }
                        >
                            Вход
                            {getIcon("вход")}
                        </Button>

                        <Button
                            className="w-full flex justify-between items-center"
                            onClick={() =>
                                selectFrom("Касса")
                            }
                        >
                            Касса
                            {getIcon("касса")}
                        </Button>

                        <Button
                            className="w-full"
                            onClick={() =>
                                selectFrom("0 этаж")
                            }
                        >
                            0 этаж
                        </Button>

                        <Button
                            className="w-full"
                            onClick={() =>
                                selectFrom("1 этаж")
                            }
                        >
                            1 этаж
                        </Button>

                        <Button
                            className="w-full"
                            onClick={() =>
                                selectFrom("2 этаж")
                            }
                        >
                            2 этаж
                        </Button>

                        <Button
                            className="w-full"
                            onClick={() =>
                                router.push("../")
                            }
                        >
                            ← Назад
                        </Button>
                    </>
                )}

                {/* STEP 2 */}
                {step === 2 && (
                    <>
                        <div className="text-left font-medium">
                            Вы выбрали: {from}
                        </div>

                        {floorLocations[from]?.map(
                            (location) => (
                                <Button
                                    key={location}
                                    className="w-full flex justify-between text-left items-center"
                                    onClick={() =>
                                        selectSubLocation(location)
                                    }
                                >
                                    {location}
                                    {getIcon(location)}
                                </Button>
                            )
                        )}

                        <Button
                            className="w-full"
                            onClick={() => setStep(1)}
                        >
                            Назад
                        </Button>
                    </>
                )}

                {/* STEP 3 */}
                {step === 3 && (
                    <>
                        <div className="border rounded-lg p-4 text-left">
                            <p>
                                <strong>
                                    Текущее положение:
                                </strong>{" "}
                                {from}
                                {subLocation &&
                                    ` → ${subLocation}`}
                            </p>
                        </div>

                        {destinations.map((destination) => (
                            <Button
                                key={destination}
                                className="w-full flex justify-between text-left items-center"
                                onClick={() =>
                                    buildRoute(destination)
                                }

                            >
                                {destination}
                                {getIcon(destination)}
                            </Button>
                        ))}

                        <Button
                            className="w-full"
                            onClick={() =>
                                setStep(
                                    subLocation ? 2 : 1
                                )
                            }
                        >
                            Назад
                        </Button>
                    </>
                )}

                {/* STEP 4 */}
                {step === 4 && (
                    <>
                        <div className="border rounded-lg p-4 flex flex-col gap-3 text-left">
                            <h2 className="text-xl font-bold">
                                Маршрут построен
                            </h2>

                            <p>
                                <strong>
                                    Откуда:
                                </strong>{" "}
                                {from}
                                {subLocation &&
                                    ` → ${subLocation}`}
                            </p>

                            <p>
                                <strong>
                                    Куда:
                                </strong>{" "}
                                {to}
                            </p>

                            <div className="flex flex-col gap-2 mt-2">
                                <p>
                                    1. Двигайтесь прямо 20 метров.
                                </p>
                                <p>
                                    2. Поверните направо.
                                </p>
                                <p>
                                    3. Пройдите 5 метров прямо.
                                </p>
                                <p>
                                    4. Вы прибыли в пункт назначения.
                                </p>
                            </div>
                        </div>

                        <Button
                            className="w-full text-left"
                            onClick={resetRoute}
                        >
                            Построить новый маршрут
                        </Button>
                    </>
                )}
            </div>
        </div>
    );
}